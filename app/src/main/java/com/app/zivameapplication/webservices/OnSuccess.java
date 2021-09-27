package com.app.zivameapplication.webservices;


import com.app.zivameapplication.webservices.ResponseWrapper;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface OnSuccess<ResponseType> {
    Response<ResponseType> onSuccess(ResponseWrapper<ResponseBody> data);
}
