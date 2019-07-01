let InfoDialog = Vue.component('info-dialog', {
    props: {
        message: {
            type: Object,
            required: false,
        }
    },
    
    methods: {
        
        close(){
            this.$emit ('onclose');
        }
    },
    template: '#info-dialog-template'
});


let YesNoDialog = Vue.component('yesno-dialog', {
    props: {
        message: {
            type: Object,
            required: false,
        },
        confirmationfunc: {
            type: Function,
            required: true
        },
        disagreementfunc: {
            type: Function,
            required: false
        },
        cancelfunc: {
            type: Function,
            required: false
        }
    },
    
    data() {
        return {
            confirm: false
        }
    },
    template: '#yesno-dialog-template'
});