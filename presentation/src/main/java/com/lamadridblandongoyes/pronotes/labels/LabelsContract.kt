package com.lamadridblandongoyes.pronotes.labels

import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface LabelsContract {
    interface View: BaseView {
        fun updateAdapterWith(labels: ArrayList<Label>)
        fun navigateTowardsLabelEditionWith(label: Label?)
        fun askForDeletionConfirmationWith(index: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun onAddButtonTapped()
        fun processAddingLabelResult(label: Label?)
        fun processEditingLabelResult(label: Label?)
        fun processItemTappedWith(index: Int)
        fun processItemLongTappedWith(index: Int)
        fun deletionConfirmedWith(index: Int)
    }
}