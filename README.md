# Google-Platform-Notes

**Jaki błąd znalazłem ostatnio?**

- In Sabre deployment system, in UI, row with the same minute with couple of ticket don't appears.
- After library update, way of generating JSON response failed (LinkedHashMap was changed to HashMap, no order preserved).


**Jaki najtrudniejszy projekt i dlaczego?**

- SLP echo server.
- Ratelimit, mainly configs in bazel.
- Migrate logging to JSON format during GCP migration.
