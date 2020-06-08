package com.example.elshamelapp.view.viewModel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.elshamelapp.data.model.TabNumModel;

public class HomePageViewModel extends ViewModel {
    private TabNumModel tabNumModel;
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            if (input.equals(1)){
//                tabNumModel=new TabNumModel("tabe1");
//                tabNumModel.setTabeNum();
                return "tabe num1";
            }else if (input.equals(2)){
                return "tabe num2";
            }else if (input.equals(3)){
                return "tabe num3";
            }else if (input.equals(4)){
                return "tabe num4";
            }else if (input.equals(5)){
                return "tabe num5";
            }else if (input.equals(6)){
                return "tabe num6";
            }
            return "tabe" + input;
        }
    });

    public void setIndex(int index) {
        mIndex.postValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}