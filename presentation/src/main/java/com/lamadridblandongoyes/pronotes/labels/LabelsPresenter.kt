package com.lamadridblandongoyes.pronotes.labels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class LabelsPresenter(
    private val getAllLabelsInteractor: FlowableUseCase<List<Label>, Unit>,
    private val insertLabelInteractor: ObservableUseCase<Long, Label>,
    private val updateLabelInteractor: ObservableUseCase<Int, Label>,
    private val deleteLabelInteractor: ObservableUseCase<Int, Label>
): BasePresenter<LabelsContract.View>, LabelsContract.Presenter {

    override var view: LabelsContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var labels: ArrayList<Label> = ArrayList<Label>()


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun start() {
        fetchLabels()
    }

    override fun onAddButtonTapped() {
        view?.navigateTowardsLabelEditionWith(label = null)
    }

    override fun processAddingLabelResult(label: Label?) {
        label?.let {
            subscriptions.add(
                this.insertLabelInteractor
                    .execute(it,
                        {
                            labels.add(label)
                            this.updateAdapter()
                        }, { error ->
                            this.handleException(error)
                        })
            )
        }
    }

    override fun processEditingLabelResult(label: Label?) {
        label?.let {
            subscriptions.add(
                this.updateLabelInteractor
                    .execute(it,
                        {
                            labels.indexOfFirst {
                                it.labelId == label.labelId
                            }.let { indexToRemove ->
                                labels.removeAt(indexToRemove)
                                labels.add(label)
                                this.updateAdapter()
                            }
                        }, { error ->
                            this.handleException(error)
                        })
            )
        }
    }

    override fun processItemTappedWith(index: Int) {
        labels[index].let { label ->
            view?.navigateTowardsLabelEditionWith(label = label)
        }
    }

    override fun processItemLongTappedWith(index: Int) {
        view?.askForDeletionConfirmationWith(index)
    }

    override fun deletionConfirmedWith(index: Int) {
        labels[index].let { label ->
            subscriptions.add(
                this.deleteLabelInteractor
                    .execute(label,
                        {
                            labels.removeAt(index)
                            this.updateAdapter()
                        }, {
                            this.handleException(it)
                        })
            )
        }
    }

    private fun fetchLabels() {
        subscriptions.add(
            getAllLabelsInteractor.execute(Unit,{ extractedLabels ->
                this.labels = ArrayList( extractedLabels )
                this.updateAdapter()
            },{
                this.handleException(it)
            })
        )
    }

    private fun updateAdapter() {
        view?.updateAdapterWith(this.labels)
    }
}