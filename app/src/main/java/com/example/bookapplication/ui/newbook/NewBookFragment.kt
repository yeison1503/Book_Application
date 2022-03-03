package com.example.bookapplication.ui.newbook

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bookapplication.databinding.FragmentNewBookBinding
import java.text.SimpleDateFormat
import java.util.*

class NewBookFragment : Fragment() {


    private lateinit var newBookBinding: FragmentNewBookBinding
    private lateinit var newBookviewModel: NewBookViewModel

    private var cal = Calendar.getInstance()
    private var publicationDate = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        newBookBinding = FragmentNewBookBinding.inflate(inflater, container, false)
        newBookviewModel = ViewModelProvider(this).get(NewBookViewModel::class.java)
        return newBookBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newBookviewModel.msgDone.observe(viewLifecycleOwner, { result ->
            onMsgDoneSuscribe(result)
        })

        newBookviewModel.dataValidated.observe(viewLifecycleOwner, { result ->
            onDateValidateSuscribe(result)
        })


        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            newBookBinding.publicationDateButton.text = publicationDate
        }

        with(newBookBinding) {
            publicationDateButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {
                newBookviewModel.validateFields(
                    nameBookEditText.text.toString(),
                    nameAuthorEditText.text.toString(),
                    pagesEditText.text.toString()
                )
            }
        }
    }

private fun onDateValidateSuscribe(result: Boolean) {
    with(newBookBinding) {
        val nameBook = nameBookEditText.text.toString()
        val author = nameAuthorEditText.text.toString()
        val pages = pagesEditText.text.toString().toInt()
        val resumen= abstractEditText.text.toString()

        var genre = ""
        if (suspenseCheckBox.isChecked) genre = "Suspenso"
        if (terrorCheckBox.isChecked) genre += "Terror"
        if (infantileCheckBox.isChecked) genre += "Infantil"
        if (fictionCheckBox.isChecked) genre += "Ficción"

        // var score = if (oneRadioButton.isChecked) 1 else 2
        val score = when {
            oneRadioButton.isChecked -> 1
            twoRadioButton.isChecked -> 2
            threeRadioButton.isChecked -> 3
            fourRadioButton.isChecked -> 4
            else -> 5
        }
        //Guarda el libro en modo local
        //newBookviewModel.saveBook(nameBook, author, pages, resumen, genre, score, publicationDate)

        newBookviewModel.saveBookServer(nameBook, author, pages, resumen, genre, score, publicationDate)
    }
}

private fun onMsgDoneSuscribe(msg: String) {
    Toast.makeText(
        requireContext(),
        msg,
        Toast.LENGTH_SHORT
    ).show()
}
}