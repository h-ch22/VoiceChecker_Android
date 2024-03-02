package com.cj.voicechecker.userManagement.helper

import com.cj.voicechecker.frameworks.helper.AES256Util
import com.cj.voicechecker.userManagement.models.UserInfoModel
import com.cj.voicechecker.userManagement.models.UserManagementAlertTypeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserManagement {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    companion object {
        private var userInfo: UserInfoModel? = null

        fun getUserInfo(): UserInfoModel? {
            return userInfo
        }

        fun setUserInfo(userInfo: UserInfoModel?) {
            this.userInfo = userInfo
        }
    }

    fun signIn(
        email: String,
        password: String,
        completion: (UserManagementAlertTypeModel?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    getUserInfo {
                        completion(if (it) UserManagementAlertTypeModel.SUCCESS else UserManagementAlertTypeModel.UNKNOWN_ERROR)
                    }
                } else {
                    completion(UserManagementAlertTypeModel.UNKNOWN_ERROR)
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                completion(UserManagementAlertTypeModel.UNKNOWN_ERROR)
                return@addOnFailureListener
            }
    }

    private fun getUserInfo(completion: (Boolean) -> Unit) {
        db.collection("Users").document(auth.currentUser?.uid ?: "").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val document = it.result

                    if (document != null) {
                        val nickName = document.get("nickName") as? String ?: ""
                        val name = document.get("name") as? String ?: ""
                        val phone = document.get("phone") as? String ?: ""
                        val email = document.get("email") as? String ?: ""

                        setUserInfo(
                            UserInfoModel(
                                AES256Util.decrypt(email),
                                AES256Util.decrypt(name),
                                AES256Util.decrypt(phone),
                                AES256Util.decrypt(nickName)
                            )
                        )

                        completion(true)
                        return@addOnCompleteListener
                    } else {
                        completion(false)
                        return@addOnCompleteListener
                    }
                } else {
                    completion(false)
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                completion(false)
                return@addOnFailureListener
            }
    }

    fun signUp(
        email: String,
        password: String,
        name: String,
        phone: String,
        nickName: String,
        completion: (UserManagementAlertTypeModel?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    this.setUserInfo(name, phone, nickName){
                        completion(if(it) UserManagementAlertTypeModel.SUCCESS else UserManagementAlertTypeModel.UNKNOWN_ERROR)
                    }
                } else {
                    completion(UserManagementAlertTypeModel.UNKNOWN_ERROR)
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                completion(UserManagementAlertTypeModel.UNKNOWN_ERROR)
                return@addOnFailureListener
            }
    }

    private fun setUserInfo(name: String, phone: String, nickName: String, completion: (Boolean) -> Unit) {
        db.collection("Users").document(auth.currentUser?.uid ?: "").set(
            mapOf(
                "name" to AES256Util.encrypt(name),
                "phone" to AES256Util.encrypt(phone),
                "nickName" to AES256Util.encrypt(nickName),
                "email" to AES256Util.encrypt(auth.currentUser?.email ?: "")
            )
        ).addOnCompleteListener {
            completion(it.isSuccessful)
            return@addOnCompleteListener
        }.addOnFailureListener {
            it.printStackTrace()
            completion(false)
            return@addOnFailureListener
        }
    }
}