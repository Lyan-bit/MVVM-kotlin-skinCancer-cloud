package com.example.skincancer.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.skincancer.R
import com.example.skincancer.SkinCancerBean
import com.example.skincancer.viewmodel.SkinViewModel
import java.lang.Exception
import java.util.ArrayList

//Delete Skin
class DeleteSkinFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: SkinViewModel
	private lateinit var skinBean: SkinCancerBean

	private lateinit var deleteskinSpinner: Spinner
	private var allSkinids: List<String> = ArrayList()
	private lateinit var idTextView: TextView
	private var idData = ""
	private lateinit var okButton: Button
	private lateinit var cancelButton: Button

	companion object {
		fun newInstance(c: Context): DeleteSkinFragment {
			val fragment = DeleteSkinFragment()
			val args = Bundle()
			fragment.arguments = args
			fragment.myContext = c
			return fragment
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.deleteskin_layout, container, false)
		super.onViewCreated(root, savedInstanceState)
		return root
	}

	override fun onResume() {
		super.onResume()
		model = SkinViewModel.getInstance(myContext)

		idTextView = root.findViewById(R.id.deleteSkinidTextView)
		deleteskinSpinner = root.findViewById(R.id.deleteSkinSpinner)

		Log.i("model",model.listSkinCancer().toString())
		Log.i("allSkinCancerids", model.allSkinCancerids().toString())
		allSkinids = model.allSkinCancerids()
		val deleteskinAdapter =
				ArrayAdapter(myContext, android.R.layout.simple_spinner_item, allSkinids)
		deleteskinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		deleteskinSpinner.adapter = deleteskinAdapter
		deleteskinSpinner.onItemSelectedListener = this

		skinBean = SkinCancerBean(myContext)
		okButton = root.findViewById(R.id.deleteSkinOK)
		okButton.setOnClickListener(this)
		cancelButton = root.findViewById(R.id.deleteSkinCancel)
		cancelButton.setOnClickListener(this)
	}

	override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
		if (parent === deleteskinSpinner) {
			idTextView.setText(allSkinids[position])
			idData = allSkinids[position]
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>?) {
		//onNothingSelected
	}

	override fun onClick(v: View) {
		val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		try {
			imm.hideSoftInputFromWindow(v.windowToken, 0)
		} catch (e: Exception) {
			e.printStackTrace()
		}

		when (v.id) {
			R.id.deleteSkinOK -> {
				deleteSkinOK()
			}
			R.id.deleteSkinCancel -> {
				deleteSkinCancel()
			}
		}
	}

	private fun deleteSkinOK() {
		skinBean.setId(idData)
		if (skinBean.isDeleteSkinCancerError(allSkinids)) {
			Log.w(javaClass.name, skinBean.errors())
			Toast.makeText(myContext, "Errors: " + skinBean.errors(), Toast.LENGTH_LONG).show()
		} else {
			skinBean.deleteSkinCancer()
			Toast.makeText(myContext, "Skin deleted!", Toast.LENGTH_LONG).show()
			deleteSkinCancel()
			}
	}

	private fun deleteSkinCancel() {
		skinBean.resetData()
		idTextView.setText("")
	}
}

