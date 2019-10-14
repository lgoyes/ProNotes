package com.lamadridblandongoyes.pronotes.noteedition

import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface NoteEditionContract {
    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {

    }
}