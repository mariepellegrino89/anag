package filters;

import model.LoggingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import repository.LoggingModelRepository;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static com.google.common.base.Charsets.UTF_8;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Autowired
    private LoggingModelRepository loggingModelRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            Timestamp requestDate = Timestamp.from(Instant.now());

            HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
            requestWrapper.setAttribute("UUID", UUID.randomUUID().toString());
            saveLoggingModelRequest(request, requestWrapper, requestDate);

            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
            filterChain.doFilter(requestWrapper, responseWrapper);


            LoggingModel loggingModel = settingLoggingModelResponse(responseWrapper, requestWrapper);

            responseWrapper.copyBodyToResponse();
            Date date = null;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
                date = formatter.parse(responseWrapper.getHeader("Date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (loggingModel != null) {
                loggingModel.setResponseTimestamp(date != null ? Timestamp.from(Instant.ofEpochMilli(date.getTime())) : null);
                loggingModelRepository.save(loggingModel);
            }
    }

    private LoggingModel settingLoggingModelResponse(ContentCachingResponseWrapper responseWrapper, HttpRequestWrapper requestWrapper) throws IOException {
        if(requestWrapper.getAttribute("UUID")!=null) {
            Optional<LoggingModel> loggingModelOptional= loggingModelRepository.findById(requestWrapper.getAttribute("UUID").toString());
            if (loggingModelOptional.isPresent()){
                LoggingModel loggingModel = loggingModelOptional.get();
                loggingModel.setOutputPayload(convert(responseWrapper.getContentInputStream(), UTF_8));
                return loggingModel;
            }
        }
        return null;
    }

    public String convert(InputStream inputStream, Charset charset) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }

    private void saveLoggingModelRequest(HttpServletRequest request, HttpRequestWrapper requestWrapper, Timestamp requestDate){
        LoggingModel model = new LoggingModel();
        model.setRequestTimestamp(requestDate);
        model.setRequestId(requestWrapper.getAttribute("UUID").toString());
        model.setInputPayload(requestWrapper.getBody());
        model.setEndPoint(requestWrapper.getRequestURL().toString());
        model.setOperation(requestWrapper.getMethod());

        loggingModelRepository.save(model);
    }

}
