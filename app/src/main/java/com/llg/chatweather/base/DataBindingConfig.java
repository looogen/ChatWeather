package com.llg.chatweather.base;

import android.util.SparseArray;

import androidx.lifecycle.ViewModel;

/**
 * 配置各种绑定的参数
 */
public class DataBindingConfig {

    private int layout;

    private int vmVariableId;

    private ViewModel stateViewModel;

    private SparseArray<Object> bindingParams = new SparseArray<>();

    public DataBindingConfig(int layout, int vmVariableId, ViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }

    public DataBindingConfig(int layout) {
        this.layout = layout;
    }


    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray<Object> getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}