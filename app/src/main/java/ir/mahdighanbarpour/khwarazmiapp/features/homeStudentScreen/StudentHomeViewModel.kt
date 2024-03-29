package ir.mahdighanbarpour.khwarazmiapp.features.homeStudentScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.mahdighanbarpour.khwarazmiapp.model.data.ExamsMainResult
import ir.mahdighanbarpour.khwarazmiapp.model.repositories.ExamRepository

class StudentHomeViewModel(private val examRepository: ExamRepository) : ViewModel() {

    // Determines whether something is being received from the server or not
    val isPopularExamsDataLoading: BehaviorSubject<Boolean> = BehaviorSubject.create()

    // Getting the list of popular exams from the server based on the user's grade
    fun getPopularExams(grade: String): Single<ExamsMainResult> {
        isPopularExamsDataLoading.onNext(true)

        return examRepository.getPopularExams(grade).doFinally {
            isPopularExamsDataLoading.onNext(false)
        }
    }
}