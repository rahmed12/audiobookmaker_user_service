curl -X POST http://localhost:8080/v1/api/users/create \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "strongpassword123"
}'