package com.lamadridblandongoyes.pronotes.notes

import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface NotesContract {
    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {

    }
}