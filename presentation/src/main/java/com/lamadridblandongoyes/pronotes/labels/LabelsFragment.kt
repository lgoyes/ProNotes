package com.lamadridblandongoyes.pronotes.labels

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.*
import com.lamadridblandongoyes.pronotes.labeledition.LabelEditionActivity
import com.lamadridblandongoyes.pronotes.notes.ItemTapListener
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_labels.*
import javax.inject.Inject

class LabelsFragment: DaggerFragment(),
    LabelsContract.View,
    ItemTapListener {

    @Inject
    lateinit var presenter: LabelsContract.Presenter

    private var labelsAdapter: LabelsAdapter =
        LabelsAdapter(this)

    private lateinit var recyclerView: RecyclerView

    private lateinit var addButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_labels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViews()
        setupRecyclerView()
        setupAddButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun updateAdapterWith(labels: ArrayList<Label>) {
        this.labelsAdapter.clearLabels()
        this.labelsAdapter.addLabels(labels)
        this.labelsAdapter.notifyDataSetChanged()
    }

    override fun navigateTowardsLabelEditionWith(label: Label?) {
        val intent = Intent(activity, LabelEditionActivity::class.java)

        if (label == null) {
            startActivityForResult(intent, ADDING_LABEL_REQUEST_CODE)
            return
        }

        label.let {
            intent.putExtra(INTENT_EXTRA_LABEL, label)
            startActivityForResult(intent, EDITING_LABEL_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            ADDING_LABEL_REQUEST_CODE -> {
                presenter.processAddingLabelResult(data?.extras?.getParcelable(INTENT_EXTRA_LABEL))
            }
            EDITING_LABEL_REQUEST_CODE -> {
                presenter.processEditingLabelResult(data?.extras?.getParcelable(INTENT_EXTRA_LABEL))
            }
        }
    }

    override fun askForDeletionConfirmationWith(index: Int) {
        val builder = AlertDialog.Builder(activity)
            .apply {
                setTitle(REMOVE_LABEL_TITLE)
                setMessage(REMOVE_LABEL_SUBTITLE)
                setPositiveButton(android.R.string.yes) { _, _ ->
                    presenter.deletionConfirmedWith(index)
                }
                setNegativeButton(android.R.string.no) { _, _ -> }
            }
        builder.show()
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onItemTapped(index: Int) {
        presenter.processItemTappedWith(index= index)
    }

    override fun onItemLongTapped(index: Int): Boolean {
        presenter.processItemLongTappedWith(index = index)
        return true
    }

    private fun bindViews() {
        this.recyclerView = labels_recyclerview
        this.addButton = labels_floating_action_button
    }

    private fun setupRecyclerView() {
        this.recyclerView.layoutManager = LinearLayoutManager(context)

        this.recyclerView.adapter = labelsAdapter
    }

    private fun setupAddButton() {
        this.addButton.setOnClickListener {
            presenter.onAddButtonTapped()
        }
    }

    companion object {
        fun newInstance(): LabelsFragment {
            return LabelsFragment()
        }
    }
}