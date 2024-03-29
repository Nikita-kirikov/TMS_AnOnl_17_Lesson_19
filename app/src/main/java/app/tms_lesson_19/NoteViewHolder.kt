package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val headerView : TextView = root.findViewById(R.id.header)
    private val textView : TextView = root.findViewById(R.id.text)
    private val dateView : TextView = root.findViewById(R.id.date)



    fun bind(note: Note, click: ((Note) -> Unit)?, onLongClick: ((Note) -> Unit)?) {
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
        fun from(parent : ViewGroup) : NoteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val noteView = layoutInflater.inflate(R.layout.note_item, parent, false)
            return NoteViewHolder(noteView)
        }
    }
}