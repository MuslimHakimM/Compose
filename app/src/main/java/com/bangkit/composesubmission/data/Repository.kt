package com.bangkit.composesubmission.data

import com.bangkit.composesubmission.model.FavMotor
import com.bangkit.composesubmission.model.MotorDataDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {

    private val favMotor = mutableListOf<FavMotor>()

    init {
        if (favMotor.isEmpty()) {
            MotorDataDetail.listMotor.forEach {
                favMotor.add(FavMotor(it, 0))
            }
        }
    }

    fun getAllMotor(): Flow<List<FavMotor>> {
        return flowOf(favMotor)
    }

    fun getMotorFavoriteById(motorId: Long): FavMotor {
        return favMotor.first {
            it.motor.id == motorId
        }
    }

    fun updateOrderReward(motorId: Long, newCountValue: Int): Flow<Boolean> {
        val index = favMotor.indexOfFirst { it.motor.id == motorId }
        val result = if (index >= 0) {
            val orderMotor = favMotor[index]
            favMotor[index] =
                orderMotor.copy(motor = orderMotor.motor, merchCount = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderRewards(): Flow<List<FavMotor>> {
        return getAllMotor()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.merchCount != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}