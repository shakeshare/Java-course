package LogAccumulate;

import java.util.*;

public class LogAccumulate {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<String> ip = new HashSet<String>();
        Map<String, Integer> logCount = new HashMap<String, Integer>();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String logEntry = sc.nextLine().trim();
            String[] parts = logEntry.split(" ");
            String user = parts[0];
            String name = parts[1];
            if (user.equalsIgnoreCase("LOGIN")) {
                if (ip.contains(name) == false) {
                    ip.add(name);
                    logCount.put(name, logCount.getOrDefault(name, 0) + 1);
                }
            } else if (user.equalsIgnoreCase("LOGOUT")) {
                if (ip.contains(name)) {
                    ip.remove(name);
                }
            }

        }
        sc.close();
        System.out.println(ip.size());
        List<String> sortedNames = new ArrayList<>(logCount.keySet());
        Collections.sort(sortedNames);
        for (int i = 0; i < sortedNames.size(); i++) {
            String name = sortedNames.get(i);
            if (i == 0) {
                System.out.print(name + ':' + logCount.get(name));
            } else {

                System.out.print(',' + name + ':' + logCount.get(name));
            }
        }
        return;
    }
}
