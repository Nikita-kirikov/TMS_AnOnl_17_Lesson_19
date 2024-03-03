package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class NoteImportantViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val headerView : TextView = root.findViewById(R.id.header)
    private val textView : TextView = root.findViewById(R.id.text)
    private val dateView : TextView = root.findViewById(R.id.date)



    fun bind(note : Note, click : ((Note) -> Unit)?, onLongClick: ((Note) -> Unit)?) {
        headerView.text = note.header
        textView.text = note.text
        dateView.text = note.date

        itemView.setOnClickListener {
            click?.invoke(note)
        }

        itemView.setOnLongClickListener() {
            onLongClick?.invoke(note)
            true
        }
    }


    companion object {
        fun from(parent : ViewGroup) : NoteImportantViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val noteView = layoutInflater.inflate(R.layout.note_important_item, parent, false)
            return NoteImportantViewHolder(noteView)
        }
    }
}