package com.lamadridblandongoyes.pronotes.labeledition

import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.IErrorHandler
import io.reactivex.disposables.CompositeDisposable

class LabelEditionPresenter: BasePresenter<LabelEditionContract.View>, LabelEditionContract.Presenter {
    override var view: LabelEditionContract.View? = null
    override var errorHandler: IErrorHandler? = null
    override val subscriptions = CompositeDisposable()

    private var labelUnderEdition: Label? = null


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

    override fun setLabelUnderEdition(labelUnderEdition: Label?) {
        this.labelUnderEdition = labelUnderEdition
    }

    private fun isValid(data: LabelEditionContract.View.ViewModel): Boolean {
        return data.title.isNotEmpty()
    }

    private fun wrapEditedData(viewModel: LabelEditionContract.View.ViewModel): Label {
        return Label(
            labelUnderEdition?.labelId,
            viewModel.title,
            viewModel.color
        )
    }

}