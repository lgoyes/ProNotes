package com.lamadridblandongoyes.pronotes.labels

import com.lamadridblandongoyes.domain.interactors.base.FlowableUseCase
import com.lamadridblandongoyes.domain.interactors.base.ObservableUseCase
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.DELETE_LABEL_INTERACTOR
import com.lamadridblandongoyes.pronotes.UPDATE_LABEL_INTERACTOR
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class LabelsModule {
    @Provides
    fun provideLabelsPresenter(
        getAllLabelsInteractor: FlowableUseCase<List<Label>, Unit>,
        insertLabelInteractor: ObservableUseCase<Long, Label>,
        @Named(UPDATE_LABEL_INTERACTOR) updateLabelInteractor: ObservableUseCase<Int, Label>,
        @Named(DELETE_LABEL_INTERACTOR) deleteLabelInteractor: ObservableUseCase<Int, Label>
    ): LabelsContract.Presenter =
        LabelsPresenter(
            getAllLabelsInteractor,
            insertLabelInteractor,
            updateLabelInteractor,
            deleteLabelInteractor
        )
}