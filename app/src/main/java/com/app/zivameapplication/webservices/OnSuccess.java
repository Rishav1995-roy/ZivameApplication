package com.app.zivameapplication.webservices;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface OnSuccess<ResponseType> {
    Response<ResponseType> onSuccess(ResponseWrapper<ResponseBody> data);
}
