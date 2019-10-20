package com.lamadridblandongoyes.pronotes.labeledition

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.*
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_label_edition.*
import javax.inject.Inject

class LabelEditionActivity: DaggerAppCompatActivity(), LabelEditionContract.View, ColorEnvelopeListener {

    @Inject
    lateinit var presenter: LabelEditionContract.Presenter

    private lateinit var formTitle: AppCompatTextView
    private lateinit var formSubtitle: AppCompatTextView
    private lateinit var labelTitle: AppCompatEditText
    private lateinit var saveButton: AppCompatButton
    private lateinit var labelColorTextView: AppCompatTextView
    private lateinit var selectColorButton: AppCompatImageButton

    private var selectedColor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_edition)
        bindViews()
        setupSaveButton()
        setupSelectColorButton()
        extractIntentExtras()

        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun getFormData(): LabelEditionContract.View.ViewModel =
        LabelEditionContract.View.ViewModel(
            labelTitle.text.toString(),
            selectedColor
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
        labelColorTextView = label_edition_label_color
        selectColorButton = label_edition_select_color_button
    }

    private fun setupSaveButton() {
        this.saveButton.setOnClickListener {
            presenter.onSaveButtonTapped()
        }
    }

    private fun setupSelectColorButton() {
        this.selectColorButton.setOnClickListener {
            presenter.onSelectColorTapped()
        }
    }

    private fun extractIntentExtras() {
        (intent.extras?.getParcelable(INTENT_EXTRA_LABEL) as? Label)?.let{
            presenter.setLabelUnderEdition(it)
        }
    }

    override fun setFormTitle(text: String) {
        formTitle.text = text
    }

    override fun setFormSubtitle(text: String) {
        formSubtitle.text = text
    }

    override fun setLabelTitle(text: String) {
        labelTitle.setText(text)
    }

    override fun setLabelColor(hexColor: String) {
        selectedColor = hexColor
        labelColorTextView.setBackgroundColor(Color.parseColor(hexColor))
    }

    override fun setLabelColorText(text: String) {
        labelColorTextView.text = text
    }

    override fun presentColorPickerDialog() {
        ColorPickerDialog.Builder(this)
            .setTitle(PICK_THE_LABEL_COLOR)
            .setPositiveButton(android.R.string.ok, this)
            .setNegativeButton(android.R.string.cancel) { dialogInterface: DialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .attachAlphaSlideBar(false)
            .attachBrightnessSlideBar(false)
            .show()
    }

    override fun onColorSelected(envelope: ColorEnvelope?, fromUser: Boolean) {
        envelope?.let {
            setLabelColor( "#" + it.hexCode )
        }
    }
}