package com.app.zivameapplication.webservices;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface OnException<ResponseType> {
    Response<ResponseType> onException(ResponseWrapper<ResponseBody> data);
}
