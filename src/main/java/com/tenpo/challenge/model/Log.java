
package com.tenpo.challenge.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "api_log")
@NoArgsConstructor
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Long unixTimestamp;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "querystring")
    private String queryString;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    public Log(String endpoint,String request,String response) {
        this.endpoint = endpoint;
        this.request = request;
        this.response = response;
        this.unixTimestamp = System.currentTimeMillis() / 1000;
    }

}

