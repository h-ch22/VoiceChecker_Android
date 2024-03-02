package com.cj.voicechecker.userManagement.models

enum class UserManagementAlertTypeModel : UserManagementAlertType {
    UNKNOWN_USER {
        override fun getTitle(): String {
            return "일치하는 사용자 없음"
        }

        override fun getMessage(): String {
            return "입력한 정보를 다시 확인한 후 다시 시도하십시오."
        }
    },

    PASSWORD_MISMATCH{
        override fun getTitle(): String {
            return "비밀번호 불일치"
        }

        override fun getMessage(): String {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다."
        }
    },

    EMAIL_ALREADY_IN_USE{
        override fun getTitle(): String {
            return "이미 사용 중인 E-Mail"
        }

        override fun getMessage(): String {
            return "비밀번호 재설정을 시도하거나, 다른 E-Mail을 입력하십시오."
        }
    },

    INCORRECT_EMAIL_TYPE{
        override fun getTitle(): String {
            return "올바르지 않은 형식의 E-Mail"
        }

        override fun getMessage(): String {
            return "올바른 형식의 E-Mail을 입력하십시오."
        }
    },

    WEAK_PASSWORD{
        override fun getTitle(): String {
            return "안전하지 않은 비밀번호"
        }

        override fun getMessage(): String {
            return "보안을 위해 6자리 이상의 비밀번호를 입력하십시오."
        }
    },

    UNKNOWN_ERROR{
        override fun getTitle(): String {
            return "알 수 없는 오류"
        }

        override fun getMessage(): String {
            return "요청하신 작업을 처리하는 중 문제가 발생하였습니다.\n네트워크 상태를 확인하거나 나중에 다시 시도하십시오."
        }
    },

    SIGN_OUT_FAIL{
        override fun getTitle(): String {
            return "로그아웃 실패"
        }

        override fun getMessage(): String {
            return "로그아웃을 진행하는 중 문제가 발생하였습니다.\n정상 로그인 여부, 네트워크 상태를 확인하거나 나중에 다시 시도하십시오."
        }
    },

    LEAVE_MEMBERSHIP_FAIL{
        override fun getTitle(): String {
            return "회원탈퇴 실패"
        }

        override fun getMessage(): String {
            return "회원탈퇴를 진행하는 중 문제가 발생하였습니다.\n정상 로그인 여부, 네트워크 상태를 확인하거나 나중에 다시 시도하십시오."
        }
    },

    SUCCESS{
        override fun getTitle(): String {
            return ""
        }

        override fun getMessage(): String {
            return ""
        }
    },
}