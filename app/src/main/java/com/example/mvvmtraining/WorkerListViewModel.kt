package com.example.mvvmtraining

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WorkerListViewModel:ViewModel() {
    val workers= mutableStateListOf<Worker>(Worker("Программист", 1000000000),Worker("Сантехник", 150000), Worker("Экономист", 500000))
    var newJob by mutableStateOf("")

    var newSalary by mutableStateOf(0)

    fun addWorker(){
        workers.add(Worker(newJob,newSalary))
    }

    fun changeJob(job:String){
        newJob=job
    }
    fun changeSalary(salary:Int){
        newSalary=salary
    }

    fun changeWorker(job: String){
        workers.forEach{
            if(job==it.job){
                it.job=newJob
                it.salary=newSalary
            }
        }
    }
    fun deleteWorker(worker: Worker){
        workers.remove(worker)
    }
}