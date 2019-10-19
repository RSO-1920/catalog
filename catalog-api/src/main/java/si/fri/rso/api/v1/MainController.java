package si.fri.rso.api.v1;


import si.fri.rso.lib.responses.ResponseDTO;

public class MainController {

    public ResponseDTO responseOk(String message, Object data) {
        return new ResponseDTO(200, message, data);
    }

    public ResponseDTO responseBadRequest(String message) {
        return new ResponseDTO(400, message, new Object());
    }

    public ResponseDTO responseServerError(String message) {
        return new ResponseDTO(500, message, new Object());
    }

}
