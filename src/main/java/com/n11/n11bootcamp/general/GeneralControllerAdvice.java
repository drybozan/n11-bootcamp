package com.n11.n11bootcamp.general;

import java.time.LocalDateTime;

import com.n11.n11bootcamp.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controllera istek atıldığında 200 dışında durum verdiğinde yani herhangi bir yerde hata aldığında
 * proje yapısı hakkında bir sürü detay döndürüyor kullanıcıya. Mümkün oldukça paketlerimi ve proje mimarimi saklamam gerekiyor.
 * O yüzden proje herhangi bir exception aldığında onu sarmalamam lazım exception handler aracılığıyla.
 */


@ControllerAdvice
@RestController // controller ' ları buraya tabii tutacağım.
@RequiredArgsConstructor
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    // private final KafkaProducerService kafkaProducerService;

    // En genel exception metotu bu. eger ozel yazılan exception metotu yoksa exceptionlar bu metota dusecek.
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {

        /*
        handler etmeden once kullanıcıya donen mesaj aşağıdaki gibidir.
        {
          "timestamp": "2024-04-25T12:26:32.388+00:00",
          "status": 500,
          "error": "Internal Server Error",
          "trace": "java.util.NoSuchElementException: No value present\n\tat java.base/java.util.Optional.get(Optional.java:143)\n\
          tat com.n11.n11bootcamp.general.BaseEntityService.findByIdWithControl(BaseEntityService.java:58)\n\
          tat com.n11.n11bootcamp.controller.contract.impl.CustomerControllerContractImpl.getCustomerById(CustomerControllerContractImpl.java:75)\
          n\tat com.n11.n11bootcamp.controller.CustomerController.getCustomerById(CustomerController.java:54)\n\tat
          java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.
          reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)\n\tat java.base/..
          "message": "No value present",
          "path": "/api/customers/3"
        }*/

        String message = e.getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages); //RestResponse classımızda ki static error fonksiyonuna erisiyoruz

        // kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR); // http durumu kodunu verip controller tarafından kullanılmasına izin veriyoruz.
       /*
       * handler ettikten sonra şu şekilde yanıt dondurulur kullanıcıya:
                {
              "data": {
                "date": "2024-04-25T15:50:05.442354374",
                "message": "No value present",
                "description": "uri=/api/customers/3"
              },
              "responseDate": "2024-04-25T15:50:05.442482613",
              "messages": null,
              "success": false
            }*/

        }

    // runtime exception daha ozel bir exception. Burada runtime exception ı handle edecek metot varken buraya duser daha geneline dusmez.

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(BusinessException e, WebRequest request) {

        String message = e.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

        //kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRTExceptions(ItemNotFoundException e, WebRequest request) {

        String message = e.getBaseErrorMessage().getMessage();
        String description = request.getDescription(false);

        GeneralErrorMessages generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        RestResponse<GeneralErrorMessages> restResponse = RestResponse.error(generalErrorMessages);

       // kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
         /*{
              "data": {
                "date": "2024-04-26T09:58:42.422821905",
                "message": "Item not found!",
                "description": "uri=/api/customers/3"
              },
              "responseDate": "2024-04-26T09:58:42.422849272",
              "messages": null,
              "success": false
           }*/
    }
/*
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<Map<String, String>> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(),
                            fieldError.getDefaultMessage());
                    return errorMap;
                }).toList();

        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), errorList.toString(), description);
        var restResponse = RestResponse.error(generalErrorMessages);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(TransactionSystemException e, WebRequest request) {

        String message = e.getOriginalException().getCause().getMessage();
        String description = request.getDescription(false);

        var generalErrorMessages = new GeneralErrorMessages(LocalDateTime.now(), message, description);
        var restResponse = RestResponse.error(generalErrorMessages);

        kafkaProducerService.sendMessage("errorLog", message);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



   */
}
