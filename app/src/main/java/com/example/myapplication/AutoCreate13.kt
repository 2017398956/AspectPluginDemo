package com.example.myapplication

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import personal.nfl.permission.support.R
import personal.nfl.permission.support.constant.ApplicationConstant
import personal.nfl.permission.support.util.AbcPermission
import personal.nfl.permission.support.view.Permission23Fragment
import personal.nfl.permission.support.view.Permission23Fragment.PermissionsHandler

/**
 * java 代码中，通过 aop 申请权限
 * 你也可以把改文件转成 java 代码来测试 MainActivity 和 Main2Activity
 * （这里是由 abcpermission-plugin 自动生成的，这里只是展示了 aspectj 的使用，实际使用时用你自己的代码即可）
 */
@Aspect
class AutoCreate13 {
    private val permissions =
        arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CALL_PHONE")
    private var proceedingJoinPoint: ProceedingJoinPoint? = null

    @Pointcut("call(* com.example.myapplication.Main2Activity.readFile(..))")
    fun methodCall() {
    }

    @Pointcut("execution(* com.example.myapplication.Main2Activity.readFile(..))")
    fun methodExe() {
    }

    @Before("methodCall()")
    fun beforeCall(joinPoint: JoinPoint?) {
        Log.i("NFL", "beforeCall Permission23Fragment")
        if (Build.VERSION.SDK_INT >= 14) {
            val viewGroup =
                ApplicationConstant.nowActivity.window.decorView.findViewById<ViewGroup>(android.R.id.content)
            var frameLayout =
                ApplicationConstant.nowActivity.findViewById<FrameLayout>(R.id.permission23)
            if (null == frameLayout) {
                frameLayout = FrameLayout(ApplicationConstant.nowActivity)
                frameLayout.visibility = View.GONE
                frameLayout.setBackgroundColor(Color.GREEN)
                frameLayout.id = R.id.permission23
                viewGroup.addView(frameLayout)
            }
            val fragmentManager = ApplicationConstant.nowActivity.fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val permission23Fragment = Permission23Fragment()
            permission23Fragment.setPermissionsHandler(permissionsHandler)
            fragmentTransaction.add(R.id.permission23, permission23Fragment)
            fragmentTransaction.commit()
            frameLayout.post { permission23Fragment.requestPermissions(permissions) }
        }
    }

    @Around("methodExe()")
    fun aroundExe(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        Log.i("NFL", "in Permission23Fragment exe")
        if (Build.VERSION.SDK_INT < 14) {
            return try {
                proceedingJoinPoint.proceed()
            } catch (throwable: Throwable) {
                AbcPermission.permissionListener.exeException(throwable)
                null
            }
        } else {
            this.proceedingJoinPoint = proceedingJoinPoint
        }
        return null
    }

    @After("methodCall()")
    fun afterCall(joinPoint: JoinPoint?) {
        Log.i("NFL", "after Permission23Fragment exe")
    }

    private val permissionsHandler = PermissionsHandler {
        try {
            proceedingJoinPoint!!.proceed()
        } catch (throwable: Throwable) {
            AbcPermission.permissionListener.exeException(throwable)
        }
    }
}