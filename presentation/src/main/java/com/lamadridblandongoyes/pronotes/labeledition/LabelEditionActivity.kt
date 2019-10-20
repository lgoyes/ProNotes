package com.lamadridblandongoyes.pronotes.labeledition

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.INTENT_EXTRA_LABEL
import com.lamadridblandongoyes.pronotes.LABEL_VALIDATION_ERROR_SUBTITLE
import com.lamadridblandongoyes.pronotes.LABEL_VALIDATION_ERROR_TITLE
import com.lamadridblandongoyes.pronotes.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_label_edition.*
import javax.inject.Inject

class LabelEditionActivity: DaggerAppCompatActivity(), LabelEditionContract.View {

    @Inject
    lateinit var presenter: LabelEditionContract.Presenter

    private lateinit var formTitle: AppCompatTextView
    private lateinit var formSubtitle: AppCompatTextView
    private lateinit var labelTitle: AppCompatEditText
    private lateinit var saveButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_edition)
        bindViews()
        setupSaveButton()
        fillForm()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun getFormData(): LabelEditionContract.View.ViewModel =
        LabelEditionContract.View.ViewModel(
            labelTitle.text.toString(),
            "#FF0000"
        )

    override fun navigateBackWith(label: Label?) {
        label?.let {
            val resultIntent = Intent()
            resultIntent.putExtra(INTENT_EXTRA_LABEL,label)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }

    override fun showValidationError() {
        val builder = AlertDialog.Builder(this)
            .apply {
                setTitle(LABEL_VALIDATION_ERROR_TITLE)
                setMessage(LABEL_VALIDATION_ERROR_SUBTITLE)
                setPositiveButton(android.R.string.ok, { _, _ -> })
            }
        builder.show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun bindViews() {
        formTitle = label_edition_form_title
        formSubtitle = label_edition_form_subtitle
        labelTitle = label_edition_label_title_field
        saveButton = label_edition_save_button
    }

    private fun setupSaveButton() {
        this.saveButton.setOnClickListener {
            presenter.onSaveButtonTapped()
        }
    }

    private fun fillForm() {
        val labelUnderEdition: Label? = intent.extras?.getParcelable(INTENT_EXTRA_LABEL)

        if (labelUnderEdition == null) {
            fillFormForNewLabel()
            return
        }

        labelUnderEdition.let {
            presenter.setLabelUnderEdition(labelUnderEdition)
            fillFormForEditingLabelWith(it)
        }
    }

    private fun fillFormForNewLabel() {
        formTitle.setText( R.string.new_label)
        formSubtitle.setText(R.string.new_label_subtitle)
    }

    private fun fillFormForEditingLabelWith(label: Label) {
        formTitle.setText( R.string.edit_label )
        formSubtitle.setText( R.string.edit_label_subtitle )
        labelTitle.setText(label.title)
    }
}