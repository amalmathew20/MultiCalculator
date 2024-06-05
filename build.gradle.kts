plugins 
{
    kotlin("multiplatform") version "1.8.21"
}


repositories 
{
    mavenCentral()
}


kotlin 
{

    jvm()
    js(IR)


    {
        browser()
        nodejs()
    }


    linuxX64()
    macosX64()
    ios()

    sourceSets 
    
    
    {
        val commonMain by getting 
        
        {
            dependencies 
            
            {
                implementation(kotlin("stdlib-common"))
            }

        }
        val commonTest by getting 
        
        
        {
            dependencies 
            
            
            {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }


        }

        val jvmMain by getting
        val jvmTest by getting 
        
        
        {
            dependencies 
            
            {
                implementation(kotlin("test-junit"))
            }


        }

        val jsMain by getting
        val jsTest by getting 
        
        {
            dependencies 
            
            {
                implementation(kotlin("test-js"))
            }

        }

        val linuxX64Main by getting
        val macosX64Main by getting
        val iosMain by getting
    }
    
}
