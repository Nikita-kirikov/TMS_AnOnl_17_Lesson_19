package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    val textView : TextView = root.findViewById(R.id.group)

    fun bind(group: Group, onGroupClick : ((Group) -> Unit)?) {
        textView.text = group.text

        itemView.setOnLongClickListener {
            onGroupClick?.invoke(group)
            true
        }
    }

    companion object {
        fun from(parent: ViewGroup) : GroupViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val groupView = layoutInflater.inflate(R.layout.group_item, parent, false)
            return GroupViewHolder(groupView)
        }
    }
}