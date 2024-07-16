package com.example.mvvmtraining

import android.icu.number.NumberFormatter.UnitWidth
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmtraining.ui.theme.MVVMTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var yandexCompany:WorkerListViewModel= androidx.lifecycle.viewmodel.compose.viewModel()
            androidx.compose.foundation.layout.Column {
                com.example.mvvmtraining.WorkerData(
                    job = yandexCompany.newJob,
                    salary = yandexCompany.newSalary,
                    newJob = yandexCompany.changeJob(),
                    newSalary = yandexCompany.changeSalary()
                )
                com.example.mvvmtraining.WorkerList(workers = yandexCompany.workers, delete = yandexCompany.deleteWorker())
            }
        }
    }
}


@Composable
fun WorkerList(workers:List<Worker>,delete:(Worker)->(Unit)){
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(workers){
            Text(text = it.job, modifier = Modifier.padding(30.dp),fontSize=24.sp)
            Text(text = it.salary.toString(), modifier = Modifier.padding(30.dp),fontSize=24.sp)
            Button(onClick = { delete(it) }) {
                Text(text = "Удалить")
            }
        }
    }
}


@Composable
fun WorkerData(job:String,salary:Int,newJob:(String)->Unit,newSalary:(Int)->Unit,add:()->Unit){
    Column {
        OutlinedTextField(value = job, onValueChange = {newJob(it.toString())}, modifier = Modifier.padding(30.dp), label = {
            Text(text = "Введите должность")
        })
        OutlinedTextField(value =salary.toString(), onValueChange = {newSalary(it.toInt())}, modifier = Modifier.padding(30.dp), label = {
            Text(text = "Введите зарплату")
        })
        Button(onClick = {add()}) {
            Text(text = "Добавить!")
        }
    }
}