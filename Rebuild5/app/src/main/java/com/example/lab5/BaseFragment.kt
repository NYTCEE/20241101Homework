package com.example.rebuild5

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(javaClass.simpleName, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(javaClass.simpleName, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(javaClass.simpleName, "onCreateView")
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(javaClass.simpleName, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e(javaClass.simpleName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(javaClass.simpleName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(javaClass.simpleName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(javaClass.simpleName, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(javaClass.simpleName, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(javaClass.simpleName, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(javaClass.simpleName, "onDetach")
    }
}