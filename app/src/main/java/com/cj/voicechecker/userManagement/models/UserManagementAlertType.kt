package com.cj.voicechecker.userManagement.models

interface UserManagementAlertType {
    fun getTitle(): String
    fun getMessage(): String
}