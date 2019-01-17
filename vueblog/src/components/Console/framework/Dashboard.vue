<template>
    <div>
        Welcome back, {{name}}
    </div>
</template>

<script>
    export default {
        data() {
            return {
                name: localStorage.getItem('ms_username')
            }
        },
        created() {
            this.$http.get('/dashboard');
        }
    }
</script>



