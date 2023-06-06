package devandroid.medleyfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class alunos : AppCompatActivity() {

    private lateinit var alunoRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var alunosList: ArrayList<dbAlunos>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos)

        alunoRecyclerView = findViewById(R.id.listAlunos)
        alunoRecyclerView.layoutManager = LinearLayoutManager(this)
        alunoRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        alunosList = arrayListOf<dbAlunos>()

        getAlunosData()
    }
    private fun getAlunosData() {
        alunoRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Aluno")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                alunosList.clear()
                if(snapshot.exists()) {
                    for(empSnap in snapshot.children) {
                        val dData =empSnap.getValue(dbAlunos::class.java)
                        alunosList.add(dData!!)
                    }
                    val vAdapter = AdapterAcademia(alunosList)
                    alunoRecyclerView.adapter = vAdapter

                    alunoRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}