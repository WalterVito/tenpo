
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

    public Log(String endpoint) {
        this.endpoint = endpoint;
        this.unixTimestamp = System.currentTimeMillis() / 1000;
    }

    /*
    @Type(type = "jsonb")
    @Column(columnDefinition = "request")
    private SumDto request;
    @Type(type = "jsonb")
    @Column(columnDefinition = "response")
    private ResultDto response;
*/
}

