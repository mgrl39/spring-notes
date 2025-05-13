package net.xeill.elpuig.springbootrestcontrollervscontroller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreetingService {
    private List<String> messages = new ArrayList<String>();

    public void addWord(String w) {
        messages.add(w);
    }

    public List<String> getAllMessages() {
        return messages;
    }
}
