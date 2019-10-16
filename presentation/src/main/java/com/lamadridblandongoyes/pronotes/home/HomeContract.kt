package com.lamadridblandongoyes.pronotes.home

import com.lamadridblandongoyes.pronotes.architecturebasis.BasePresenter
import com.lamadridblandongoyes.pronotes.architecturebasis.BaseView

interface HomeContract {
    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {

    }
}