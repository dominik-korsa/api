package io.github.wulkanowy.api.repository

import io.github.wulkanowy.api.interceptor.ErrorHandlerTransformer
import io.github.wulkanowy.api.service.HomepageService
import io.reactivex.Maybe
import io.reactivex.Single

class HomepageRepository(private val api: HomepageService) {

    fun getSelfGovernments(): Single<List<String>> {
        return api.getSelfGovernments().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getStudentsTrips(): Single<List<String>> {
        return api.getStudentsTrips().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getLastGrades(): Single<List<String>> {
        return api.getLastGrades().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getFreeDays(): Single<List<String>> {
        return api.getFreeDays().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getKidsLuckyNumbers(): Single<List<String>> {
        return api.getKidsLuckyNumbers().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getKidsLessonPlan(): Single<List<String>> {
        return api.getKidsLessonPlan().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getLastHomework(): Single<List<String>> {
        return api.getLastHomework().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getLastTests(): Single<List<String>> {
        return api.getLastTests().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    fun getLastStudentLessons(): Single<List<String>> {
        return api.getLastStudentLessons().compose(ErrorHandlerTransformer()).map { it.data }.map { it[0].content }.map { res ->
            res.map { it.name }
        }
    }

    @Deprecated("Deprecated due to VULCAN homepage update 19.06", ReplaceWith("getKidsLuckyNumbers()"))
    fun getLuckyNumber(): Maybe<Int> {
        // return api.getKidsLuckyNumbers()
        return Maybe.just(-1)
    }
}
