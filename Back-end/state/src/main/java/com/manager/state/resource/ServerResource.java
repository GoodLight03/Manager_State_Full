package com.manager.state.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.state.model.Response;
import com.manager.state.model.Server;
import com.manager.state.model.Status;
import com.manager.state.service.ServerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/server")
public class ServerResource {
    @Autowired
    ServerService serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServer() throws InterruptedException{
        TimeUnit.SECONDS.sleep(3);
        //throw new InterruptedException("Somthing wwent wrong");
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("server",serverService.list(30)))
                .message("Server retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAdress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAdress") String ipAdress) throws IOException{
        Server server =serverService.ping(ipAdress);
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("server",server))
                .message(server.getStatus()==Status.SERVER_UP ? "Ping Success" :"Ping Failed")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid Server server){
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("server",serverService.create(server)))
                .message("Server created")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build()
        );
    }

    @GetMapping("/get/{ip}")
    public ResponseEntity<Response> get(@PathVariable("ip") Long ip) throws IOException{
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("server",serverService.get(ip)))
                .message("Server retried")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @DeleteMapping("/delete/{ip}")
    public ResponseEntity<Response> delete(@PathVariable("ip") Long ip){
        return ResponseEntity.ok(
            Response.builder()
                .timeStamp(LocalDateTime.now())
                .data(Map.of("delete",serverService.delete(ip)))
                .message("Server deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
        );
    }

    @GetMapping(path = "/image/{fileName}",produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
    public byte[] getFile(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("D:\\Full_Manger_State\\Back-end\\state\\src\\main\\resources\\static\\image\\"+fileName));
    }
}
