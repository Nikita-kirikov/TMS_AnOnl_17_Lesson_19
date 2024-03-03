package app.tms_lesson_19
/*
-Создать список Recycler View с заметками
-Заметка: заголовок, текст, дата (можно рандомную)
-Хранить список заметок можно в отдельном Object (как стаитичяеский)
-На том же экране добавить поля для создания заметик (получается 2-3 поля и кнопка)
-Добавление заметок реализовать с помощью notify методов (только не notifyDataChanged)
-Реализовать вывод сообщения через клик по заметке
----
Дополнительно (лучше сделать)
-Создать 2 ViewType (заголовок группы заметок) - (можно воспринимать заполненное поле заголовка и пустое текста как то что вы хотите добавить группу)
-Вынести создание ViewHolder и его Bind внутрь класса
-Использовать Sealed для Data классов
-Добавить вывод какого-то другого сообщения по долгому тапу по заметке
-Реализовать клик по второму вью тайп (можно через 2 функциональные переменные, можно и одной и уже на моменте передачи проверять тип)

-Добавить чекбокс важное/не важное (чистить чекбокс после добавления заметки) - и сделайте красный (можно красноватый) фон для важных заметок
 */

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var headerAdd  : TextInputEditText
    private lateinit var textAdd : TextInputEditText
    private lateinit var button : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var myAdapter : NoteAdapter
    private lateinit var important : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        actionNote()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        myAdapter = NoteAdapter()
        myAdapter.notes = SingletonList.getNotes()
        recyclerView.adapter = myAdapter

        headerAdd = findViewById(R.id.header_add)
        textAdd = findViewById(R.id.text_add)
        button = findViewById(R.id.button_add)
        important = findViewById(R.id.important)

    }

    private fun actionNote() {
        button.setOnClickListener {
            createNewItem()
        }

        myAdapter.onClick = {
            Toast.makeText(this, "Note ${it.header} is clicked", Toast.LENGTH_SHORT).show()
        }

        myAdapter.onGroupClick = {
            Toast.makeText(this, "Group ${it.text} is clicked", Toast.LENGTH_SHORT).show()
        }

        myAdapter.onLongClick = {
            SingletonList.importantItem(it)
            myAdapter.notifyItemChanged(myAdapter.itemCount)
        }
    }

    private fun createNewItem() {
        val header = headerAdd.text.toString().trim()
        val text = textAdd.text.toString().trim()

        val newItem = if (text.isEmpty()) {
            Group(header)
        } else if (important.isChecked) {
            Note(header, text, Date().toString(), true)
        } else {
            Note(header, text, Date().toString())
        }

        SingletonList.addItem(newItem)
        myAdapter.notes = SingletonList.getNotes()

        myAdapter.notifyItemInserted(myAdapter.itemCount)

        headerAdd.text?.clear()
        textAdd.text?.clear()
        important.isChecked = false
    }
}