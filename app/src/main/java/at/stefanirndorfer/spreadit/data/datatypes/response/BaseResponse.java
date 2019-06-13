package at.stefanirndorfer.spreadit.data.datatypes.response;

import android.os.Parcelable;

import androidx.annotation.Nullable;

public abstract class BaseResponse implements Parcelable {

    @Nullable
    public abstract <T extends BaseResponseBuilder> T newBuilder();

    public static abstract class BaseResponseBuilder<U> {

        public abstract U build();
    }
}
