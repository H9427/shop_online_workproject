<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 150px">
            <div style="font-size: 25px;font-weight: bold">注册新用户</div>
            <div style="font-size: 14px;color: grey;margin-top: 5px;">欢迎注册我们的购物平台，请在下方填写相关信息</div>
        </div>
        <div style="margin-top: 50px">
            <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
                <el-form-item prop="username">
                    <el-input v-model="form.username" :maxlength="8" type="text" placeholder="用户名">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="form.password" :maxlength="16" type="password" placeholder="密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password_repeat">
                    <el-input v-model="form.password_repeat" :maxlength="16" type="password" placeholder="重复密码">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
            </el-form>
        </div>
        <div style="margin-top: 50px">
            <el-button style="width: 270px" type="warning" @click="register" plain>立即注册</el-button>
        </div>
        <div style="margin-top: 20px">
            <span style="font-size: 14px;line-height: 15px;color: grey">已有账号? </span>
            <el-link type="primary" style="translate: 0 -2px" @click="router.push('/login')">立即登录</el-link>
        </div>
    </div>
</template>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {get, post} from "@/net";
import 'element-plus/dist/index.css'

const form = reactive({
    username: '',
    password: '',
    password_repeat: '',
})

const validateUsername = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
        callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
    } else {
        callback()
    }
}

const validatePassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
}

const rules = {
    username: [
        { validator: validateUsername, trigger: ['blur', 'change'] },
        { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 16, message: '密码的长度必须在6-16个字符之间', trigger: ['blur', 'change'] }
    ],
    password_repeat: [
        { validator: validatePassword, trigger: ['blur', 'change'] },
    ]
}

const formRef = ref()

const register = () => {
    formRef.value.validate((isValid) => {
        if(isValid) {
            post('/api/auth/register', {
                username: form.username,
                password: form.password,
            }, () => {
                ElMessage.success('注册成功，欢迎加入我们')
                router.push("/login")
            })
        } else {
            ElMessage.warning('请完整填写注册表单内容！')
        }
    })
}
</script>

<style scoped>

</style>
