package springboot.shuttle.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;



@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor//websocket message hendling을 허용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override // MessageBroker는 송신자에게 수신자의 이전 메세지 프로토콜로 변환해주는 모듈 중 하나
    // 요청이 오면 그에 해당하는 통신 채널로 전송, 응답 또한 같은 경로로 가서 응답한다.
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub"); //메세지 응담 prefix 구독 이경로 구독 메세지 뿌리기
        registry.setApplicationDestinationPrefixes("/pub"); //클라이언트에서 메세지 송신 시 붙여줄 prefix 푸쉬 send
        //pub은 메시지 발행 sub 메시지 구독
        //이 방식은 구독자 관리가 알아서 되서 세션관리를 할 필요가 없음 stomp 좋은점
    }

    @Override // 최초 소켓 연결 시 endpoint 접속 주소 ws://localhost:830/stomp/chat
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat").addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("http://localhost:830").withSockJS(); // javascript에서 SockJS생성자를 통해 연결
    }

}
