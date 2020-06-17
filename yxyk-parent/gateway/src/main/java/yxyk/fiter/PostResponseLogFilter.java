package yxyk.fiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author deng
 * @date 2020/6/16 0016
 */

@Slf4j
@Component
public class PostResponseLogFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        HttpServletRequest request =RequestContext.getCurrentContext().getRequest();

        LOGGER.info(">>> response status:{}, request uri is: {}", response.getStatus(), request.getRequestURI());

        return null;
    }
}
