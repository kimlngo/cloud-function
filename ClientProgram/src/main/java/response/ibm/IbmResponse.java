package response.ibm;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class IbmResponse<T> {
    private static final Gson GSON = new Gson();
    private Class<T> type;
    
    private String activationId;
    private JsonArray annotations;
    private String name;
    private int duration;

    private IbmInnerResponse<T> response;

    public IbmResponse(Class<T> clsT) {
        this.type = clsT;
    }

    public String getActivationId() {
        return activationId;
    }

    public void setActivationId(String activationId) {
        this.activationId = activationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return this.response.getStatus();
    }

    public boolean isSuccess() {
        return this.response.isSuccess();
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
    
    public int getWaitTime() throws Exception {
        try {
            JsonObject waitTime= getAnnotationValue("waitTime");
            return waitTime.get("value").getAsInt();
        } catch (Exception e) {
            throw e;
        }
    }

    public int getAllocatedMemory() throws Exception {
        try {
            JsonObject limits = getAnnotationValue("limits");
            JsonObject value = (JsonObject) limits.get("value");
            return value.get("memory").getAsInt();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private JsonObject getAnnotationValue(String key) throws Exception {
        for (int i = 0; i < this.annotations.size(); i++) {
            JsonObject jsonObject = (JsonObject) this.annotations.get(i);

            if (key.equals(jsonObject.get("key").getAsString())) {
                return jsonObject;
            }
        }
        throw new Exception("Item not found");
    }
    
    public T getResult() {
        String json = GSON.toJson(this.response.getResult());
        return GSON.fromJson(json, type);
    }

    public IbmInnerResponse<T> getResponse() {
        return response;
    }
}

class IbmInnerResponse<T> {
    private T result;
    private String status;
    private boolean success;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "IbmInnerResponse [result=" + result + ", status=" + status + ", success=" + success + "]";
    }
}
