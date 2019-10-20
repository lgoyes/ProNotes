package com.lamadridblandongoyes.pronotes.labeledition

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.*
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class LabelEditionPresenter: BasePresenter<LabelEditionContract.View>, LabelEditionContract.Presenter {
    override var view: LabelEditionContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var labelUnderEdition: Label? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start() {
        view?.setLabelColorText(LABEL_COLOR)

        if (labelUnderEdition == null) {
            fillFormForNewLabel()
        } else {
            fillFormForEditingLabel()
        }
    }

    fun fillFormForNewLabel() {
        view?.setFormTitle(NEW_LABEL_TITLE)
        view?.setFormSubtitle(NEW_LABEL_SUBTITLE)
    }

    fun fillFormForEditingLabel() {
        view?.setFormTitle(EDIT_LABEL_TITLE)
        view?.setFormSubtitle(EDIT_LABEL_SUBTITLE)
        labelUnderEdition?.let {
            view?.setLabelTitle(it.title)
            view?.setLabelColor(it.color)
        }
    }

    override fun onSaveButtonTapped() {
        view?.getFormData()?.let { data ->
            if(isValid(data)) {
                view?.navigateBackWith(
                    wrapEditedData(data)
                )
            }else{
                view?.showValidationError()
            }
        }
    }

    override fun onSelectColorTapped() {
        view?.presentColorPickerDialog()
    }

    override fun setLabelUnderEdition(labelUnderEdition: Label?) {
        this.labelUnderEdition = labelUnderEdition
    }

    private fun isValid(data: LabelEditionContract.View.ViewModel): Boolean {
        return data.title.isNotEmpty() && data.color.isNotEmpty()
    }

    private fun wrapEditedData(viewModel: LabelEditionContract.View.ViewModel): Label {
        return Label(
            labelUnderEdition?.labelId,
            viewModel.title,
            viewModel.color
        )
    }

}