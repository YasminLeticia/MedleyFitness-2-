package devandroid.medleyfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import devandroid.medleyfitness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nome = binding.FormNome
        var matricula = binding.FormMatricula

        dbRef = FirebaseDatabase.getInstance().getReference("Aluno")

        binding.btCheckin.setOnClickListener {

            val fnome = binding.FormNome.text.toString()
            val fmatricula = binding.FormMatricula.text.toString()


            if(fnome.isNotEmpty() && fmatricula.isNotEmpty()) {

                val userId = dbRef.push().key!!

                val dadosAluno = dbAlunos(fnome,fmatricula)


                dbRef.child(userId).setValue(dadosAluno).addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()

                    nome.text.clear()
                    matricula.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent(this, alunos::class.java)
            startActivity(intent)
        }
    }
}