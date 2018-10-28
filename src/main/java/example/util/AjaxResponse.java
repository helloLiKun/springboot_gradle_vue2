package example.util;

public enum AjaxResponse {

    SUCCEEDED(0, "成功"), FAILED(400, "失败"), ERROR(500, "错误");

    private int code;
    private String msg;

    AjaxResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Body body() {
        return new Body(this.code, this.msg);
    }

    public class Body {
        private int code;
        private String msg;
        private Object tag;

        public Body(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }
    }
}
