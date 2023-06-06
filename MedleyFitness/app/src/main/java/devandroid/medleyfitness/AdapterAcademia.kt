package devandroid.medleyfitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterAcademia(private val alunosList: ArrayList<dbAlunos>) :
    RecyclerView.Adapter<AdapterAcademia.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val AlunoNome : TextView = itemView.findViewById(R.id.cardAluno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.alunos_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNome = alunosList[position]
        holder.AlunoNome.text = currentNome.userNome
    }

    override fun getItemCount(): Int {
        return alunosList.size
    }
}