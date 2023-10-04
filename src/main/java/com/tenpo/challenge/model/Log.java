
package com.tenpo.challenge.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "api_log")
@NoArgsConstructor
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

    @Column(name = "response" , length=10485760)
    private String response;

    public Log(String endpoint,String request,String response,String qs) {
        this.endpoint = endpoint;
        this.request = request;
        this.response = response;
        this.queryString = qs;
        this.unixTimestamp = System.currentTimeMillis() / 1000;
    }

}

