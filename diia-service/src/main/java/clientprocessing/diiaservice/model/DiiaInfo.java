package clientprocessing.diiaservice.model;

import lombok.Builder;

@Builder
public record DiiaInfo(String passport,
                       String address) {
}
