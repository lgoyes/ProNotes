package com.lamadridblandongoyes.pronotes.labeledition

import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface LabelEditionContract {
    interface View: BaseView {
        data class ViewModel(
            val title: String,
            val color: String
        )
        fun getFormData(): LabelEditionContract.View.ViewModel
        fun navigateBackWith(label: Label?)
        fun showValidationError()
        fun setFormTitle(text: String)
        fun setFormSubtitle(text: String)
        fun setLabelTitle(text: String)
        fun setLabelColor(hexColor: String)
        fun setLabelColorText(text: String)
    }

    interface Presenter: BasePresenter<View> {
        fun onSaveButtonTapped()
        fun setLabelUnderEdition(labelUnderEdition: Label?)
    }
}